import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConfigService } from './service/config.service';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonCreateComponent } from './person-create/person-create.component';
import { PersonService } from './service/person.service';

@NgModule({
  declarations: [
    AppComponent,
    PersonListComponent,
    PersonCreateComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [ConfigService, PersonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
