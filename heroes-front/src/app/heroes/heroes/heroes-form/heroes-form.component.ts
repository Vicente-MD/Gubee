import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { ErrorDialogComponent } from 'src/app/shared/error-dialog/error-dialog.component';
import { HeroService } from '../service/hero.service';


@Component({
  selector: 'app-heroes-form',
  templateUrl: './heroes-form.component.html',
  styleUrls: ['./heroes-form.component.scss']
})
export class HeroesFormComponent implements OnInit {

  form = this.formBuilder.group({
    id: new FormControl('', { nonNullable: true }),
    name: new FormControl('', { nonNullable: true }),
    race: new FormControl('', { nonNullable: true }),
    strength: new FormControl(0, { nonNullable: true }),
    agility: new FormControl(0, { nonNullable: true }),
    dexterity: new FormControl(0, { nonNullable: true }),
    intelligence: new FormControl(0, { nonNullable: true })
  });

  constructor(
    private formBuilder: FormBuilder,
    private service: HeroService,
    private snackBar: MatSnackBar,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog,
    private location: Location) {

  }

  ngOnInit(): void {
    const hero = this.activatedRoute.snapshot.data['hero'];
    if (hero.id) {
      this.form.setValue({
        id: hero.id,
        name: hero.name,
        race: hero.race,
        strength: hero.power_stats.strength,
        agility: hero.power_stats.agility,
        dexterity: hero.power_stats.dexterity,
        intelligence: hero.power_stats.intelligence
      });
    }
  }

  onSubmit() {
    if (this.form.value.race == '' || this.form.value.name == '') {
      this.onError("Não foi possível salvar o herói. Preencha todos campos corretamente.");
    } else {
      this.service.save(this.form.value).subscribe(
        r => this.onSucess(),
        e => this.onError("Erro ao salvar herói."));
      this.onCancel();
    }
  }

  onCancel() {
    this.location.back();
  }

  private onSucess() {
    this.snackBar.open('Herói salvado com sucesso!', 'x', { duration: 4000 });
  }

  private onError(msg: string) {
    this.dialog.open(ErrorDialogComponent, { width: '250px', data: msg });
  }
}
