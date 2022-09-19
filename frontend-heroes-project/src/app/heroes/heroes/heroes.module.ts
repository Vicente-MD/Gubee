import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeroesListComponent } from './heroes-list/heroes-list.component';
import { HeroesFormComponent } from './heroes-form/heroes-form.component';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroesRoutingModule } from './heroes-routing.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    HeroesListComponent,
    HeroesFormComponent,
    HeroesComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
    HeroesRoutingModule,
    HttpClientModule
  ]
})
export class HeroesModule { }