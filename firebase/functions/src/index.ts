import * as functions from 'firebase-functions';
import { DatabaseUtils } from './service/database.service';
import { ThoughtService } from './service/thought.service';

DatabaseUtils.initDatabase();

const tS = new ThoughtService();

tS.getById('vedsslvHMEMbozD8wuiQ');

export const helloWorld = functions.https.onRequest((request, response) => {
    response.json({ data: { hello: 'world' } });
});
