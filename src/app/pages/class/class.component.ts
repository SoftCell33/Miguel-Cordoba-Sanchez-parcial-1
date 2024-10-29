import { Component, OnInit } from '@angular/core';
import { ClassService } from '../../auth/class.service';
import { Class } from '../../models/class.model';

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrl: './class.component.css'
})
export class ClassComponent implements OnInit{
  classes: Class[] = [];
  selectedClass: Class | null = null;

  constructor(private classService: ClassService) {}

  ngOnInit(): void {
    this.loadClasses();
  }

  loadClasses() {
    this.classService.getClasses().subscribe(classes => {
      this.classes = classes;
    });
  }

  createClass(): void {
    this.selectedClass = { name: '', description: '', room: '', schedule: '', teacherId: 0 };
  }

  editClass(classToEdit: Class): void {
    this.selectedClass = { ...classToEdit };
  }

  deleteClass(id: number | undefined): void {
    if (id !== undefined && confirm('¿Estás seguro de que deseas eliminar esta clase?')) {
      this.classService.deleteClass(id).subscribe(() => {
        this.loadClasses();
      });
    }
  }

  onClassSaved(): void {
    this.selectedClass = null; 
    this.loadClasses(); 
  }
}
