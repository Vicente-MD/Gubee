import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Hero } from '../model/hero';
import { HeroService } from '../service/hero.service';
import { catchError, Observable, of } from 'rxjs';
import { ConfirmDialogComponent } from 'src/app/shared/confirm-dialog/confirm-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/error-dialog/error-dialog.component';


@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.scss']
})
export class HeroesComponent implements OnInit {

  heroes$: Observable<Hero[]> | null = null;

  displayedColumns = ['id', 'name', 'race', 'powerStatsId', 'strength', 'agility', 'dexterity', 'intelligence', 'actions'];

  constructor(private service: HeroService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private dialog: MatDialog) {
    this.heroes$ = service.findAll();
    this.refresh();
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.heroes$ = this.service.findAll()
      .pipe(
        catchError(error => {
          this.onError('Erro ao carregar heróis.');
          return of([])
        })
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, { width: '250px', data: errorMsg });
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(hero: Hero) {
    this.router.navigate(['edit', hero.id], { relativeTo: this.route });
  }

  delete(hero: Hero) {
    this.service.delete(hero.id).subscribe(() => {
      this.refresh();
      this.snackBar.open('Herói removido com sucesso!', 'x', {
        duration: 5000,
        verticalPosition: 'top',
        horizontalPosition: 'center'
      });
    });
  }

  openDialog(hero: Hero) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, { width: '250px', data: hero });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.delete(hero);
      }
    });
  }
}
