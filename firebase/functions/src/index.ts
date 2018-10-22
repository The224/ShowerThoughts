import * as functions from 'firebase-functions';
import { DatabaseUtils } from './service/database.service';
import { FetchingService } from './service/fetching.service';
import { ThoughtService } from './service/thought.service';

DatabaseUtils.initDatabase();

const tS = new ThoughtService();
const fS = new FetchingService();

export const helloWorld = functions.https.onRequest((request, response) => {
    response.json({ data: { hello: 'world' } });
});

export const nextRefresh = functions.https.onRequest((request, response) => {
    response.json({ data: { nextUpdateIn: fS.getNextUpdateTime() } });
});

export const fetchNow = functions.https.onRequest((request, response) => {
    fS.fetchThoughts();
    response.json({ data: 'Fetching in progress' });
});
