
NAME=ft_hangouts.en.pdf

all: show

$(NAME): ft_hangouts.en.tex
	pandoc -V documentclass=report -o $@ $<

clean:
	/bin/rm -f ft_hangouts.en.pdf

show: $(NAME)
	mupdf ft_hangouts.en.pdf

.PHONY: clean show
