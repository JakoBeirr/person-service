import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from './config.service';
import { Person } from '../model/person';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient, private configService: ConfigService) { }

  public findAll() {
    return this.http.get<Person[]>(this.configService.baseUrl + '/persons');
  }

  public create(person: Person) {
    return this.http.post<Person>(this.configService.baseUrl + '/persons', person);
  }

  public delete(id: number) {
    return this.http.delete<Person>(this.configService.baseUrl + '/persons/' + id);
  }
}
