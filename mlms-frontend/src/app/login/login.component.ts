import { Component } from '@angular/core';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  showSpinner: boolean = false;
  isAuthenticated: boolean = false; 

  constructor(private apiService: ApiService) {}

  login(): void {
    this.showSpinner = true;

    this.apiService.login(this.username, this.password).subscribe(
      (response: any) => {
       
        this.showSpinner = false;
        const token = response.token;
       
        localStorage.setItem('token', token);

       
        this.isAuthenticated = true;

       
      },
      (error) => {
        
        this.showSpinner = false;
        alert('Invalid credentials');
      }
    );
  }
}