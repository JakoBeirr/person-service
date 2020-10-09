import { Component, OnInit } from '@angular/core';
import { Person } from '../model/person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  persons: Person[];

  constructor(private personService: PersonService) { }

  ngOnInit(): void {
    this.refresh();
  }

  refresh(): void {
    this.personService.findAll().subscribe(
      data => this.persons = data,
      error => alert(error)
    );
  }

  delete(id: number): void {
    this.personService.delete(id).subscribe(
      data => this.refresh(),
      error => alert(error)
    );
  }
}
