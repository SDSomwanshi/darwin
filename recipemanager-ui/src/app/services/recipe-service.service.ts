import { Receipe } from './../models/receipe';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpService } from './http.service';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private http: HttpService) { }

  // add this url in constant file or env.ts file
  getRecipes(): Observable<Receipe[]> {
    return this.http.get("/recipes");
  }

  getRecipe(id): Observable<Receipe> {
    return this.http.getById("/recipes", id);
  }

  deleteRecipe(id): Observable<any> {
    return this.http.deleteById("/recipe", id)
  }

  addRecipes(body): Observable<any> {
    return this.http.post("/recipe",body);
  }

}
