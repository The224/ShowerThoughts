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
     * Initialize the updating cycle
     *
     * @private
     * @memberof FetchingService
     */
    private initCountdownUpdate() {
        setInterval(() => {
            if (this.fetchingCountdown === 0) {
                this.updateThoughts();
                this.fetchingCountdown = FETCHING_INTERVAL
            } else {
                this.fetchingCountdown--;
            }
        }, 100);
    }

    /**
     * Init the injection of new thoughts to the database
     *
     * @private
     * @memberof FetchingService
     */
    private updateThoughts() {
        this.fetchThoughts()
            .then(thoughts => this.saveThoughts(thoughts))
            .catch(e => console.error('fetchThoughts have fail: ' + e));
    }

    /**
     * Fetch thoughs from a extern source
     *
     * @private
     * @returns {Thought[]} found in the source
     * @memberof FetchingService
     */
    public async fetchThoughts(): Promise<Thought[]> {
        return await request({
            url: SOURCE_URL,
            json: true
        }).then(data => {
            // TODO: verify if last thougth doens't exist
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
    private saveThoughts(newThought: Thought[]): Boolean {
        throw new Error('Not Implemented');
    }

    public lastThought(): Promise<Thought> {
        const d = new Date();
        d.setDate(d.getDate() - 2);
        return this.thoughtsRef.where('date', ">=", d).orderBy('date', 'desc').limit(1).get().then((prevSnapshot => {
            return prevSnapshot.docs[0].data() as Thought;
        }));
    }

}
