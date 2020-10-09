import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Person } from '../model/person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-person-create',
  templateUrl: './person-create.component.html',
  styleUrls: ['./person-create.component.css']
})
export class PersonCreateComponent implements OnInit {

  person: Person;

  @Output() personCreated = new EventEmitter();

  constructor(private personService: PersonService) { }

  ngOnInit(): void {
    this.person = new Person();
  }

  onSubmit() {
    this.personService.create(this.person).subscribe(
      data => {
        this.personCreated.emit();
        this.person = new Person();
      }
    );
  }
}
