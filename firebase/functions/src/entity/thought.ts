export class Thought {
    constructor(
        public id: string,
        public text: string,
        public downVote: number,
        public upVote: number,
        public date: string,
        public sourceId: string,
    ) { }
}