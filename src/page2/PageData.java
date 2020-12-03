package page2;

public class PageData {

  private final PageType nextPageType;
  private final PageType previousPageType;

  private PageData(Builder builder) {
	  
    nextPageType = builder.nextPageType;
    previousPageType = builder.previousPageType;
    
  }


  PageType getNextPageType() {
    return nextPageType;
  }

  PageType getPreviousPageType() {
    return previousPageType;
  }
  
  public static class Builder {
    
    private PageType nextPageType = PageType.EMPTY;
    private PageType previousPageType = PageType.EMPTY;
       
    public Builder nextPageType(PageType pageType) {
      this.nextPageType = pageType;
      return this;
    }
    
    public Builder previousPageType(PageType pageType) {
      this.previousPageType = pageType;
      return this;
    }
    
    public PageData build() {
      return new PageData(this);
    }
  }
}