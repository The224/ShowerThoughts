import { Thought } from "../entity/thought";

const FETCHING_INTERVAL = 3600;

/**
 * Use to retrieve thoughts from a source & inject it to the firebase database
 *
 * @export
 * @class FetchingService
 */
export class FetchingService {

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
        const thoughts = this.fetchThoughts();
        this.saveThoughts(thoughts);
    }

    /**
     * Fetch thoughs from a extern source
     *
     * @private
     * @returns {Thought[]} found in the source
     * @memberof FetchingService
     */
    private fetchThoughts(): Thought[] {
        throw new Error('Not Implemented');
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

}
