import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  baseUrl: String;

  constructor() {
    this.baseUrl = 'http://localhost:8080';
  }
}
