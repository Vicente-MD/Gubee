import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroesFormComponent } from './heroes-form/heroes-form.component';
import { HeroesResolver } from './guards/heroes.resolver';
import { Injectable } from '@angular/core';

const routes: Routes = [
  { path: '', component: HeroesComponent },
  { path: 'new', component: HeroesFormComponent, resolve: { hero: HeroesResolver } },
  { path: 'edit/:id', component: HeroesFormComponent, resolve: { hero: HeroesResolver } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

@Injectable({
  providedIn: "root"
})
export class HeroesRoutingModule { }