import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClassRoutingModule } from './class-routing.module';
import { ClassComponent } from './class.component';
import { ClassformComponent } from './classform/classform.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ClassComponent,
    ClassformComponent
  ],
  imports: [
    CommonModule,
    ClassRoutingModule,
    ReactiveFormsModule
  ]
})
export class ClassModule { }
