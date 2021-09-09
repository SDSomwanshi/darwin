import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RecipeService } from './services/recipe-service.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ReceipeDetailsComponent } from './components/receipe-details/receipe-details.component';
import { ReceipeListComponent } from './components/receipe-list/receipe-list.component';
import { HttpService } from './services/http.service';
import { FormsModule } from '@angular/forms';
import { RecipeDeleteComponent } from './components/recipe-delete/recipe-delete.component';
import { RecipeAddEditComponent } from './components/recipe-add-edit/recipe-add-edit.component';
import { ReactiveFormsModule  } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ReceipeDetailsComponent,
    ReceipeListComponent,
    RecipeDeleteComponent,
    RecipeAddEditComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [RecipeService, HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
