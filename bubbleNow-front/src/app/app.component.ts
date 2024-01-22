import {Component, Host, OnInit, ViewChild} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {MilkService} from "./models/milk.service";
import {Milk} from "./models/milk";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {NgForOf} from "@angular/common";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {PreviewComponent} from "./preview/preview.component";
import {Addition} from "./models/addition";
import {Kind} from "./models/kind";
import {Syrup} from "./models/syrup";
import {Size} from "./models/size";
import {AdditionService} from "./models/addition.service";
import {KindService} from "./models/kind.service";
import {SyrupService} from "./models/syrup.service";
import {SizeService} from "./models/size.service";
import {SelectedIngredients} from "./models/selected-ingredients";
import {OrderPopupComponent} from "./order-popup/order-popup.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgForOf, SidebarComponent, PreviewComponent, OrderPopupComponent],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit{
  title = 'bubbleNow-front';
  public milks: Milk[];
  public additions: Addition[];
  public kinds: Kind[];
  public syrups: Syrup[];
  public sizes: Size[];
  public selected: SelectedIngredients;
  @ViewChild(PreviewComponent) previewComponent!: PreviewComponent;
  @ViewChild(OrderPopupComponent) orderPopupComponent!: OrderPopupComponent;
  @ViewChild(SidebarComponent) sidebarComponent!: SidebarComponent;

  constructor(private milkService: MilkService,
              private additionService: AdditionService,
              private kindService: KindService,
              private syrupService: SyrupService,
              private sizeService: SizeService) {

    this.milks = [];
    this.kinds = [];
    this.syrups = [];
    this.additions = [];
    this.sizes = [];
    this.selected = {
      milk: null,
      kind: null,
      syrup: null,
      addition: null,
      size: null
    }
  }
  ngOnInit() {
    this.getMilks();
    this.getAdditions();
    this.getSyrups();
    this.getSizes();
    this.getKinds();
  }

  public getMilks(): void {
    this.milkService.getMilks().subscribe({
      next: (response) => {
        this.milks = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    })
  }

  public getAdditions(): void {
    this.additionService.getAdditions().subscribe({
      next: (response) => {
        this.additions = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    })
  }

  public getKinds() {
    this.kindService.getKinds().subscribe({
      next: (response) => {
        this.kinds = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    })

  }

  public getSizes() {
    this.sizeService.getSizes().subscribe({
      next: (response) => {
        this.sizes = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    })

  }

  public getSyrups() {
    this.syrupService.getSyrups().subscribe({
      next: (response) => {
        this.syrups = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    })
  }
}
