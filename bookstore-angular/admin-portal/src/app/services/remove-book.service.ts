import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import {Book} from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class RemoveBookService {

  constructor(private http:HttpClient) { }

  sendBook(bookId:number){
	let url="http://localhost:8181/book/remove";
  		let headers=new HttpHeaders({
  			'Content-Type':'application/json',
  			'x-auth-token':localStorage.getItem('xAuthToken'),
  		});
  		return this.http.post(url,bookId,{headers:headers});
   }
}
