import { TestBed } from '@angular/core/testing';

import { HeroesResolver } from './heroes.resolver';

describe('HeroesResolver', () => {
  let resolver: HeroesResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(HeroesResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
