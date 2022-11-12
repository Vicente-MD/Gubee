import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Hero } from '../model/hero';
import { first, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  private readonly apiUrl = 'heroes/';

  constructor(private httpClient: HttpClient) { }

  findAll() {
    return this.httpClient.get<Hero[]>(this.apiUrl + "getAll").pipe(first());
  }

  findById(id: string){
    return this.httpClient.get<Hero>(`${this.apiUrl}id/${id}`);
  }

  save(hero: Partial<Hero>){
    if(hero.id){
      return this.updateHero(hero);
    }
    return this.httpClient.post<Hero>(this.apiUrl, hero);
  }

  updateHero(hero: Partial<Hero>): Observable<Hero> {
    return this.httpClient.put<Hero>(`${this.apiUrl}update/${hero.id}`, hero);
  }

  delete(id: string){
    return this.httpClient.delete<Hero>(`${this.apiUrl}delete/${id}`);
  }
}
