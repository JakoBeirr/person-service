import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Person } from '../model/person';
import { Gender } from '../model/gender.enum';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-person-create',
  templateUrl: './person-create.component.html',
  styleUrls: ['./person-create.component.css']
})
export class PersonCreateComponent implements OnInit {

  person: Person;
  genders = [];

  @Output() personCreated = new EventEmitter();

  constructor(private personService: PersonService) { }

  ngOnInit(): void {
    this.person = new Person();

    for (let gender in Gender) {
        this.genders.push({key: gender, value: Gender[gender]});
    }
  }

  onSubmit(): void {
    this.personService.create(this.person).subscribe(
      data => {
        this.personCreated.emit();
        this.person = new Person();
      },
      error => console.log(error)
    );
  }
}
