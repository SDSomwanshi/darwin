import { ReceipeDetailsComponent } from './components/receipe-details/receipe-details.component';
import { ReceipeListComponent } from './components/receipe-list/receipe-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipeDeleteComponent } from './components/recipe-delete/recipe-delete.component'
import { RecipeAddEditComponent } from './components/recipe-add-edit/recipe-add-edit.component'
const routes: Routes = [
  { path: '', component: ReceipeListComponent },

  { path: 'home', component: ReceipeListComponent },
  { path: 'details/:id', component: ReceipeDetailsComponent },
  { path: 'delete/:id', component: RecipeDeleteComponent},
  { path: 'addEdit/:id', component: RecipeAddEditComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
