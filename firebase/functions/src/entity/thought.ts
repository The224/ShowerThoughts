export class Thought {
    constructor(
        public id: number,
        public text: string,
        public downVote: number,
        public upVote: number
    ) { }
}