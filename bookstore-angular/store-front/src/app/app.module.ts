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
 		 MatIconModule } from '@angular/material';
import 'hammerjs';
// Component Import

// service Import

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
	     BrowserModule,
	     AppRoutingModule,
	     MatInputModule,
		 MatButtonModule,
		 MatMenuModule,
		 MatToolbarModule, 
		 MatSelectModule, 
		 MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
