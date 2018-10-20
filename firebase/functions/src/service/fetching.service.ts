import * as request from 'request-promise';
import { Thought } from "../entity/thought";
import { sourceToThought, SOURCE_URL } from '../source';
import { DatabaseUtils } from './database.service';

const FETCHING_INTERVAL = 3600;

/**
 * Use to retrieve thoughts from a source & inject it to the firebase database
 *
 * @export
 * @class FetchingService
 */
export class FetchingService {

    private thoughtsRef = DatabaseUtils.getThoughtsCol();
    private db = DatabaseUtils.getFirestoreDb();

    /**
     * Countdown before the next update
     *
     * @private
     * @memberof FetchingService
     */
    private fetchingCountdown = FETCHING_INTERVAL;

    constructor() {
        this.initCountdownUpdate();
    }

    /**
     * Return the time, in second, before the next update
     *
     * @returns {number}
     * @memberof FetchingService
     */
    public getNextUpdateTime(): number {
        return this.fetchingCountdown + 10;
    }

    /**
     * Initialize the fetching cycle
     *
     * @private
     * @memberof FetchingService
     */
    private initCountdownUpdate() {
        setInterval(() => {
            if (this.fetchingCountdown === 0) {
                this.fetchThoughts();
                this.fetchingCountdown = FETCHING_INTERVAL
            } else {
                this.fetchingCountdown--;
            }
        }, 1000);
    }

    /**
     * Fetch thoughs from a extern source
     *
     * @private
     * @returns {Thought[]} found in the source
     * @memberof FetchingService
     */
    public fetchThoughts() {
        request({
            url: SOURCE_URL,
            json: true
        }).then(data => {
            this.saveThoughts(sourceToThought(data));
        });
    }

    /**
     * Saves thoughts pass in parameter to firebase database
     *
     * @private
     * @param {Thought[]} newThought to inject in the database
     * @returns {Boolean} confirm if the result have been succesfull
     * @memberof FetchingService
     */
    private saveThoughts(newThought: Thought[]) {
        this.lastThought().then(lastThought => {
            const batch = this.db.batch();
            for (let i = 0; i < newThought.length; i++) {
                if (newThought[i].date <= lastThought.date) {
                    newThought.splice(i);
                }
            }
            if (newThought.length > 0) {
                newThought.forEach(thought => {
                    batch.set(this.thoughtsRef.doc(), thought);
                });
                batch.commit().then(() => console.log('Batch good !')).catch(e => console.log('Batch throw error !: ' + e));
            }
        }).catch();
    }

    public lastThought(): Promise<Thought> {
        return this.thoughtsRef.orderBy('date', 'desc').limit(1).get().then((prevSnapshot => {
            return prevSnapshot.docs[0].data() as Thought;
        })).catch(() => {
            return undefined;
        });
    }

}
