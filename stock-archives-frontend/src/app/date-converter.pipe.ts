import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'dateConverter'
})
export class DateConverterPipe implements PipeTransform {

  transform(dateString: string): any {
    const value = new Date(dateString);
    return value.getUTCFullYear() + '/' + (value.getMonth() + 1) + '/' + value.getDate();
  }

}
