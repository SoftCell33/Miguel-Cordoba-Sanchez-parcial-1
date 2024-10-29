import { Component, EventEmitter, Input, Output, OnChanges, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Class } from '../../../models/class.model';
import { ClassService } from '../../../auth/class.service';

@Component({
  selector: 'app-classform',
  templateUrl: './classform.component.html',
  styleUrl: './classform.component.css'
})
export class ClassformComponent{
  @Input() class: Class | null = null;
  @Output() saved = new EventEmitter<void>();

  classForm: FormGroup;

  constructor(private fb: FormBuilder, private classService: ClassService) {
    this.classForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(30)]],
      description: ['', [Validators.required, Validators.maxLength(100)]],
      room: ['', Validators.required],
      schedule: ['', Validators.required],
      teacherId: [0, Validators.required]
    });
  }

  ngOnChanges(): void {
    
    if (this.class) {
      this.classForm.patchValue(this.class);
    } else {
      this.classForm.reset(); 
    }
  }

  save(): void {
    if (this.classForm.valid) {
      const classData: Class = this.classForm.value;

      if (this.class && this.class.id) {
        
        this.classService.updateClass(this.class.id, classData).subscribe(() => {
          this.saved.emit();
        });
      } else {
        
        this.classService.createClass(classData).subscribe(() => {
          this.saved.emit();
        });
      }

      this.classForm.reset(); 
    }
  }
}
