import { Thought } from "../entity/thought";
import { DatabaseUtils } from "./database.service";

export class ThoughtService {

    private thoughtsRef = DatabaseUtils.getThoughtsCol();

    // this.thoughtsRef.get().then(x => x.forEach(y => console.log('Id: ' + y.data().text))
    // ).catch(x => console.log('ERROR : ' + x));

    public getByPage(lastThoughId?: string, pageLenght = 30): Promise<Thought> {
        if (lastThoughId) {
            this.thoughtsRef.where('vedsslvHMEMbozD8wuiQ', '==', lastThoughId).offset(pageLenght)
        } // else {
        //     this.thoughtsRef.offset(pageLenght).get();
        // }
        throw new Error('Method Not Implemented.');
    }

    public getById(id: string) {
        this.thoughtsRef.where('id', '==', id).get()
            .then(thoughts => console.log(thoughts)).catch();
        return null;
    }

    public downVote(id: string): Promise<string> {
        throw new Error('Method Not Implemented.');
    }

    public upVote(id: string): Promise<string> {
        throw new Error('Method Not Implemented.');
    }

}