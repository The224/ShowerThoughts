import * as functions from 'firebase-functions';
import { DatabaseUtils } from './service/database.service';
import { FetchingService } from './service/fetching.service';
import { ThoughtService } from './service/thought.service';

DatabaseUtils.initDatabase();

const tS = new ThoughtService();
const fS = new FetchingService();

tS.getById('vedsslvHMEMbozD8wuiQ').then(x => console.log(x.date)).catch();

fS.lastThought();

// fS.fetchThoughts().then(x => {
//     console.log(x);
// });

export const helloWorld = functions.https.onRequest((request, response) => {
    response.json({ data: { hello: 'world' } });
});
