import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UploadImageService {

	filesToUpload:Array<File>;

  constructor() { 
  	this.filesToUpload=[];
  }

  upload(bookId:number){
  	const promise=this.makeFileRequest("http://localhost:8181/book/add/image?id="+bookId,[],this.filesToUpload);
  	
  	promise.then((result) =>{
  		console.log(result);
  	},
  	(error) =>{
  		console.log(error);
  	});
  }
  modify(bookId:number){
    console.log(this.filesToUpload);
    if(this.filesToUpload.length>0){
      const promise=this.makeFileRequest("http://localhost:8181/book/update/image?id="+bookId,[],this.filesToUpload);
    
    promise.then((result) =>{
      console.log(result);
    },
    (error) =>{
      console.log(error);
    });
    }
  }

  fileChangeEvent(fileInput: any){
  		this.filesToUpload=<Array<File>> fileInput.target.files;
  }

  makeFileRequest(url:string,params:Array<string>,files:Array<File>){

  	return new Promise((resolve,reject) =>{
  		let formData:any=new FormData();
  		let xhr=new XMLHttpRequest();
  		for(let i=0;i<files.length;i++){
  			formData.append("uploads[]",files[i],files[i].name);
  		}
  		xhr.onreadystatechange=function(){
  			if(xhr.readyState==4){
  				if(xhr.status==200){
  					console.log("image uploaded successfully!");
  				}else{
  					reject(xhr.response);
  				}
  			}
  		}
  		xhr.open("POST",url,true);
  		xhr.setRequestHeader("x-auth-token",localStorage.getItem("xAuthToken"));
  		xhr.send(formData);
  	})
  }
}
