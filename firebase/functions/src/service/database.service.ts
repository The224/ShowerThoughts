import { CollectionReference, Firestore } from '@google-cloud/firestore';
import * as admin from 'firebase-admin';
import * as functions from 'firebase-functions';

export class DatabaseUtils {

    public static thoughtsRef: CollectionReference;
    public static db: Firestore;

    public static initDatabase() {
        admin.initializeApp(functions.config().firebase);
        this.db = admin.firestore();
        this.thoughtsRef = this.db.collection('thoughts');
    }

    public static getFirestoreDb(): Firestore {
        return (this.db) ? this.db : this.notInit();
    }

    public static getThoughtsCol(): CollectionReference {
        return (this.thoughtsRef) ? this.thoughtsRef : this.notInit();
    }

    private static notInit(): any {
        throw new Error('You forget to init the Database Utils!');
    }

}
