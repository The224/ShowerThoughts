import { CollectionReference } from '@google-cloud/firestore';
import * as admin from 'firebase-admin';
import * as functions from 'firebase-functions';

export class DatabaseUtils {

    public static thoughtsRef: CollectionReference;


    public static initDatabase() {
        admin.initializeApp(functions.config().firebase);
        const db = admin.firestore();
        this.thoughtsRef = db.collection('thoughts');
    }

    public static getThoughtsCol(): CollectionReference {
        return (this.thoughtsRef) ? this.thoughtsRef : this.notInit();
    }

    private static notInit(): any {
        throw new Error('You forget to init the Database Utils!');
    }

}
