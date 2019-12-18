import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

private credential={'username':'','password':''};
private loggedIn=false;
private response:any;
  constructor(private loginService: LoginService) { }

onSubmit(){
	this.loginService.sendCredential(this.credential.username,this.credential.password).subscribe(

			res =>{
					this.response=res;
					console.log(this.response.token);
					console.log(res);
					localStorage.setItem("xAuthToken",this.response.token);
					this.loggedIn=true;
					location.reload();
			},
			error =>{
					console.log(error);
			}
		);
}
  ngOnInit() {
  	this.loginService.checkSession().subscribe(

  		res =>{
  			this.loggedIn=true;
  		},
  		error =>{
  			this.loggedIn=false;
  		}
  	);
  }

}
