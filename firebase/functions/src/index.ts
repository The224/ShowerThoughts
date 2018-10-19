import * as functions from 'firebase-functions';
import { DatabaseService } from './service/database.service';

// // Start writing Firebase Functions
// // https://firebase.google.com/docs/functions/typescript

const test = new DatabaseService();

export const helloWorld = functions.https.onRequest((request, response) => {
    response.json({ data: { hello: 'world' } });
});
