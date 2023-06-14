import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email!: string;
  password!: string;

  private login_url = "http://localhost:8080/api/user/login";

  constructor(private router: Router, public http: HttpClient) { }

  login() {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    this.http.post(this.login_url,
      {
        email: this.email,
        password: this.password,
      }, httpOptions).subscribe((response: any) => {
        window.alert(response.msg);
        if (response.code === 0) {
          window.sessionStorage.setItem("email",this.email);
          this.router.navigate(['/profile']);
        }
      });
    // window.sessionStorage.setItem("email",this.email);
    // this.router.navigate(['/profile']);
  }

  ngOnInit() { }

  goToRegister() {
    this.router.navigate(['/register']);
  }
}
