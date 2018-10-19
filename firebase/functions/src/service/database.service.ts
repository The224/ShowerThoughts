import { Firestore } from '@google-cloud/firestore';
import * as admin from 'firebase-admin';
import * as functions from 'firebase-functions';

/**
 * Use to fetch data from the firebase database
 *
 * @export
 * @class DatabaseService
 */
export class DatabaseService {

    constructor() {
        const firestore = new Firestore();
        const settings = {/* your settings... */ timestampsInSnapshots: true };
        firestore.settings(settings);

        admin.initializeApp(functions.config().firebase);
        const db = admin.firestore();
        const thoughtsRef = db.collection('thoughts');
        console.log("id:" + thoughtsRef.id);

        thoughtsRef.get().then(x => console.log('Testing : ' + x)).catch(x => console.log('ERROR : ' + x));
    }

}
