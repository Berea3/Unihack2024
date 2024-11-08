import {Case} from './case';

export class User{

    id: String;
    email: String;
    password: String;
    roles: String;

    cases?: Case[]=[];
}
