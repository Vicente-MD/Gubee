import { Injectable } from '@angular/core';
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Hero } from '../model/hero';
import { HeroService } from '../service/hero.service';

@Injectable({
  providedIn: 'root'
})
export class HeroesResolver implements Resolve<Hero> {

  constructor(private service: HeroService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Hero> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']);
    }
    return of({ id: '', name: '', race: '', powerStatsId: '', strength: 0, agility: 0, dexterity: 0, intelligence: 0 });
  }
}
