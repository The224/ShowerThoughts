import * as admin from 'firebase-admin';
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

    public async getById(id: string): Promise<Thought> {
        return await this.thoughtsRef.where(admin.firestore.FieldPath.documentId(), '==', id).get().then(thoughts => {
            return {
                id: thoughts.docs[0].id,
                ...thoughts.docs[0].data() as Thought
            };
        });
    }

    public downVote(id: string): Promise<string> {
        throw new Error('Method Not Implemented.');
    }

    public upVote(id: string): Promise<string> {
        throw new Error('Method Not Implemented.');
    }

}