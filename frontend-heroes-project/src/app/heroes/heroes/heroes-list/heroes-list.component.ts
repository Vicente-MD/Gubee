import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Hero } from '../model/hero';

@Component({
    selector: 'app-heroes-list',
    templateUrl: './heroes-list.component.html',
    styleUrls: ['./heroes-list.component.scss']
})
export class HeroesListComponent implements OnInit {

    @Input() heroes: Hero[] = [];
    @Output() add = new EventEmitter(false);
    @Output() edit = new EventEmitter(false);
    @Output() remove = new EventEmitter(false);

    displayedColumns = ['id', 'name', 'race', 'powerStatsId', 'strength', 'agility', 'dexterity', 'intelligence', 'actions'];

    constructor() { }

    ngOnInit(): void { }

    onAdd() {
        this.add.emit(true);
    }

    onEdit(hero: Hero) {
        this.edit.emit(hero);
    }

    onDelete(hero: Hero) {
        this.remove.emit(hero);
    }
}
