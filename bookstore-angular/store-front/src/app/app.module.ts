import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// Imports
import { MatInputModule,
 		 MatButtonModule,
 		 MatMenuModule,
 		 MatToolbarModule, 
 		 MatSelectModule, 
 		 MatIconModule,
      MatTabsModule } from '@angular/material';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {routing} from './app.routing';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import 'hammerjs';

// Component Import
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { MyAccountComponent } from './components/my-account/my-account.component';
// service Import
import {LoginService} from './services/login.service';
import {UserService} from './services/user.service';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import {PaymentService} from './services/payment.service';
import {ShippingService} from './services/shipping.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    MyAccountComponent,
    MyProfileComponent
  ],
  imports: [
	     BrowserModule,
	     AppRoutingModule,
	     MatInputModule,
		 MatButtonModule,
		 MatMenuModule,
		 MatToolbarModule, 
		 MatSelectModule, 
		 MatIconModule,
		 routing,
		 FormsModule,
     HttpClientModule,
     MatTabsModule,
     BrowserAnimationsModule,
     MatProgressSpinnerModule
  ],
  providers: [
  		LoginService,
  		UserService,
      PaymentService,
      ShippingService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
