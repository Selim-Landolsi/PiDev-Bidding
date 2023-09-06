<?php

namespace App\Controller;

use App\Entity\Exchange;
use App\Form\ExchangeType;
use App\Repository\ExchangeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/exchange')]
class ExchangeController extends AbstractController
{
    #[Route('/', name: 'app_exchange_index', methods: ['GET'])]
    public function index(ExchangeRepository $exchangeRepository): Response
    {
        return $this->render('exchange/index.html.twig', [
            'exchanges' => $exchangeRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_exchange_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ExchangeRepository $exchangeRepository): Response
    {
        $exchange = new Exchange();
        $form = $this->createForm(ExchangeType::class, $exchange);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $exchangeRepository->save($exchange, true);

            return $this->redirectToRoute('app_exchange_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('exchange/new.html.twig', [
            'exchange' => $exchange,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_exchange_show', methods: ['GET'])]
    public function show(Exchange $exchange): Response
    {
        return $this->render('exchange/show.html.twig', [
            'exchange' => $exchange,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_exchange_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Exchange $exchange, ExchangeRepository $exchangeRepository): Response
    {
        $form = $this->createForm(ExchangeType::class, $exchange);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $exchangeRepository->save($exchange, true);

            return $this->redirectToRoute('app_exchange_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('exchange/edit.html.twig', [
            'exchange' => $exchange,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_exchange_delete', methods: ['POST'])]
    public function delete(Request $request, Exchange $exchange, ExchangeRepository $exchangeRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$exchange->getId(), $request->request->get('_token'))) {
            $exchangeRepository->remove($exchange, true);
        }

        return $this->redirectToRoute('app_exchange_index', [], Response::HTTP_SEE_OTHER);
    }
}
