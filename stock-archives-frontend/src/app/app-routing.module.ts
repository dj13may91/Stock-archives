import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StockComponent} from "./stock/stock.component";
import {StockByNameComponent} from "./stock-by-name/stock-by-name.component";
import {StockChartsComponent} from "./stock-charts/stock-charts.component";

const routes: Routes = [
  {path: '', redirectTo: '/all', pathMatch: 'full'},
  {
    path: 'all', component: StockComponent, children: [
      // {path: 'stock', component: RecipeStartComponent},
      // {path: 'new', component: RecipeEditComponent},
      // {path: ':id', component: RecipeDetailComponent},
      // {path: ':id/edit', component: RecipeEditComponent}
    ]
  },
  {path: 'stock', component: StockByNameComponent, children: [
      {path: '', component: StockChartsComponent}
    ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
