import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Recipe} from "./recipe";
import {RECIPES} from "./mock-recipes";
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {
  private recipesUrl ='http://localhost:8080/recipes';  // URL to web api
  constructor(private http: HttpClient,) {

  }

  getRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.recipesUrl)
      .pipe(
        catchError(this.handleError<Recipe[]>('getRecipes', []))
      );
  }


  /** GET hero by id. Will 404 if id not found */
  getRecipe(id: number): Observable<Recipe> {
    const url = `${this.recipesUrl}/${id}`;
    return this.http.get<Recipe>(url).pipe(
      catchError(this.handleError<Recipe>(`getRecipe id=${id}`))
    );
  }

  getVersions(id: number): Observable<Recipe[]> {
    const url = `${this.recipesUrl}/${id}/history`;
    return this.http.get<Recipe[]>(url).pipe(
      catchError(this.handleError<Recipe[]>(`getVersion id=${id}`))
    );
  }

  postReceipt(recipe: Recipe|undefined): Observable<void>{
    const url = `${this.recipesUrl}/add`;
    return this.http.post<void>(url, recipe).pipe(
      catchError(this.handleError<void>(`add recipe`))
    );
  }

  updateReceipt(id: number, recipe: Recipe|undefined): Observable<void>{
    const url = `${this.recipesUrl}/${id}`;
    return this.http.put<void>(url, recipe).pipe(
      catchError(this.handleError<void>(`add recipe`))
    );
  }
  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
