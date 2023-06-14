import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart, registerables } from 'chart.js';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from "@angular/common/http";



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, AfterViewInit {
  @ViewChild('barChartCanvas', { static: false })
  barChartCanvas!: ElementRef;

  selectedClothes: string = '0';
  usersSchedule!: number;
  email!: string;
  allUserSchedule!: number[];

  constructor(private route: ActivatedRoute,private router: Router, public http: HttpClient) { }

  ngOnInit() {
    if(!window.sessionStorage.getItem('email')){
      window.alert("请先登录")
      this.router.navigate(['/login']);
    }

    this.email = window.sessionStorage.getItem('email')!;

    // 当前用户过关成绩
    this.http.get('http://124.221.137.186:8080/api/user/getSchedule', {
      params: { email: this.email }
    }).subscribe(
      (response: any) => {
        console.log("myscore:"+response.data);
        this.usersSchedule = response.data;
      },
      error => {
        console.error('Failed to fetch level:', error);
      }
    );

    // 所有人通关 画表
    this.http.get('http://124.221.137.186:8080/api/user/statOfSchedule').subscribe(
      (response: any) => {
        this.allUserSchedule = [];
        response.data.forEach((item: any) => {
          this.allUserSchedule.push(item);
        });
        this.createBarChart();
      },
      error => {
        console.error('Failed to fetch level:', error);
      }
    );

    this.usersSchedule = 6;
  }

  ngAfterViewInit() {
  }

  createBarChart() {
    const ctx = this.barChartCanvas.nativeElement.getContext('2d');
    Chart.register(...registerables);

    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['第一关', '第二关', '第三关','第四关', '第五关', '第六关'],
        datasets: [{
          label: '人数',
          data: this.allUserSchedule,
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        // maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              stepSize: 1
            }
          }
        },
        plugins: {
          title: {
            display: true,
            text: '闯关人数',
            font: {
              size: 16,
              weight: 'bold'
            }
          }
        }
      }
    });
  }

  enterWorld() {
    var world_url = 'http://localhost:2002?'+'email='+this.email+'&cloth='+this.selectedClothes;
    window.location.href = world_url;  // 跳转到指定页面
  }
}
