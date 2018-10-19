import { Thought } from "../entity/thought";

/**
 *
 *
 * @export
 * @class ThoughtService
 */
export class ThoughtService {

    public getByPage(pageIndex: number): Thought {
        throw new Error('Method Not Implemented.');
    }

    public getById(id: number): Thought {
        throw new Error('Method Not Implemented.');
    }

    public downVote(id: number): number {
        throw new Error('Method Not Implemented.');
    }

    public upVote(id: number): number {
        throw new Error('Method Not Implemented.');
    }

}