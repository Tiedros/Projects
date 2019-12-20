import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatInputModule,
 		 MatButtonModule,
 		 MatMenuModule,
 		 MatToolbarModule, 
 		 MatSelectModule, 
 		 MatIconModule } from '@angular/material';
import 'hammerjs';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {routing} from './app.routing';

// services Import
import  {LoginService} from './services/login.service';
import {AddBookService} from './services/add-book.service';
import {UploadImageService} from './services/upload-image.service';

// Components Import
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AddNewBookComponent } from './components/add-new-book/add-new-book.component';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    AddNewBookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
     MatInputModule,
      MatButtonModule,
       MatSelectModule,
        MatIconModule,
        MatMenuModule,
 		 MatToolbarModule,
        BrowserAnimationsModule,
        HttpClientModule,
        routing,
        MatGridListModule,
        FormsModule,
        MatSlideToggleModule,
        MatFormFieldModule
  ],
  providers: [
      LoginService,
      AddBookService,
      UploadImageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
