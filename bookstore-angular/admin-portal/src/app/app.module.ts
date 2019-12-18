import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatGridListModule} from '@angular/material/grid-list';
import { FormsModule } from '@angular/forms';
import { MatInputModule,
 		 MatButtonModule,
 		 MatMenuModule,
 		 MatToolbarModule, 
 		 MatSelectModule, 
 		 MatIconModule } from '@angular/material';
 import 'hammerjs';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {routing} from './app.routing';

import  {LoginService} from './services/login.service';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent
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
        FormsModule
  ],
  providers: [
      LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
