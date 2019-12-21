import { Component, OnInit } from '@angular/core';
import {Book} from '../../models/book';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';
import {GetBookListService} from '../../services/get-book-list.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
	private selectedBook:Book;
	private checked:boolean;
	private bookList:Book[];
	private allChecked:boolean;
	private removeBookList:Book[]=new Array();

  constructor(private getBookListService:GetBookListService,private router:Router) { }

  ngOnInit() {
  	this.getBookListService.getBookList().subscribe(

  		res =>{
  				let response:any=res;
  				console.log(response);
  				this.bookList=response;
  		},
  		error =>{
  				console.log(error);
  		}
  		);
  }

}
