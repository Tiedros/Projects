import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import {Book} from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class GetBookListService {

  constructor(private http:HttpClient) { }

   getBookList(){
	let url="http://localhost:8181/book/bookList";
  		let headers=new HttpHeaders({
  			'Content-Type':'application/json',
  			'x-auth-token':localStorage.getItem('xAuthToken'),
  		});
  		return this.http.get(url,{headers:headers});
   } 
}
