import {ModuleWithProviders} from '@angular/core';
import {Routes,RouterModule} from '@angular/router';
import {MyAccountComponent} from './components/my-account/my-account.component';



// Component Imports
import {HomeComponent} from './components/home/home.component';
import {MyProfileComponent} from './components/my-profile/my-profile.component';

const appRoutes:Routes=[

	{
		path:'',
		redirectTo:'/home',
		pathMatch:'full'
	},
	{
		path:'home',
		component:HomeComponent
	},
	{
		path:'myAccount',
		component:MyAccountComponent
	},
	{
		path:'myProfile',
		component:MyProfileComponent
	}

];

export const routing:ModuleWithProviders=RouterModule.forRoot(appRoutes);