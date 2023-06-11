import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, AfterViewInit {
  @ViewChild('barChartCanvas', { static: false })
  barChartCanvas!: ElementRef;

  selectedClothes!: string;
  email!: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.email = this.route.snapshot.paramMap.get('email')!;
  }

  ngAfterViewInit() {
    this.createBarChart();
  }

  createBarChart() {
    const ctx = this.barChartCanvas.nativeElement.getContext('2d');
    Chart.register(...registerables);

    // 从服务端获取的数据
    const dataFromServer = [4, 5, 2, 4, 5, 1];

    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['第一关', '第二关', '第三关','第四关', '第五关', '第六关'], 
        datasets: [{
          label: '人数',
          data: dataFromServer,
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
}
